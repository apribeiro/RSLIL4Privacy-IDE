/**
 * generated by Xtext
 */
package rslingo.rslil4privacy.generator;

import com.google.common.base.Objects;
import com.google.common.collect.Iterators;
import java.util.Iterator;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.generator.IGenerator;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import rslingo.rslil4privacy.rSLIL4Privacy.Attribute;
import rslingo.rslil4privacy.rSLIL4Privacy.Collection;
import rslingo.rslil4privacy.rSLIL4Privacy.Disclosure;
import rslingo.rslil4privacy.rSLIL4Privacy.Informative;
import rslingo.rslil4privacy.rSLIL4Privacy.Metadata;
import rslingo.rslil4privacy.rSLIL4Privacy.Policy;
import rslingo.rslil4privacy.rSLIL4Privacy.PrivateData;
import rslingo.rslil4privacy.rSLIL4Privacy.Recipient;
import rslingo.rslil4privacy.rSLIL4Privacy.RecipientPart;
import rslingo.rslil4privacy.rSLIL4Privacy.RefPrivateData;
import rslingo.rslil4privacy.rSLIL4Privacy.RefRecipientSource;
import rslingo.rslil4privacy.rSLIL4Privacy.RefRecipientTarget;
import rslingo.rslil4privacy.rSLIL4Privacy.RefService;
import rslingo.rslil4privacy.rSLIL4Privacy.Retention;
import rslingo.rslil4privacy.rSLIL4Privacy.Service;
import rslingo.rslil4privacy.rSLIL4Privacy.ServicePart;
import rslingo.rslil4privacy.rSLIL4Privacy.Usage;

/**
 * Generates code from your model files on save.
 * 
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#code-generation
 */
@SuppressWarnings("all")
public class RSLIL4Privacy2EddyGenerator implements IGenerator {
  @Override
  public void doGenerate(final Resource resource, final IFileSystemAccess fsa) {
    String _className = this.className(resource);
    String _plus = (_className + ".policy");
    TreeIterator<EObject> _allContents = resource.getAllContents();
    Iterator<Policy> _filter = Iterators.<Policy>filter(_allContents, Policy.class);
    final Function1<Policy, CharSequence> _function = (Policy it) -> {
      return this.compile(it);
    };
    Iterator<CharSequence> _map = IteratorExtensions.<Policy, CharSequence>map(_filter, _function);
    String _join = IteratorExtensions.join(_map, " ");
    fsa.generateFile(_plus, _join);
  }
  
  public String className(final Resource res) {
    URI _uRI = res.getURI();
    String name = _uRI.lastSegment();
    int _indexOf = name.indexOf(".");
    return name.substring(0, _indexOf);
  }
  
  public CharSequence compile(final Policy p) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("SPEC HEADER");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("ATTR NAMESPACE \"http://gaius.isri.cmu.edu/example2.owl\"");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("ATTR DESC \"");
    Metadata _metadata = p.getMetadata();
    String _description = _metadata.getDescription();
    _builder.append(_description, "\t");
    _builder.append("\"");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    {
      EList<Recipient> _recipient = p.getRecipient();
      boolean _isEmpty = _recipient.isEmpty();
      boolean _not = (!_isEmpty);
      if (_not) {
        {
          EList<Recipient> _recipient_1 = p.getRecipient();
          for(final Recipient x : _recipient_1) {
            CharSequence _compileActor = this.compileActor(x);
            _builder.append(_compileActor, "\t");
          }
        }
      }
    }
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    {
      EList<Service> _service = p.getService();
      boolean _isEmpty_1 = _service.isEmpty();
      boolean _not_1 = (!_isEmpty_1);
      if (_not_1) {
        {
          EList<Service> _service_1 = p.getService();
          for(final Service z : _service_1) {
            CharSequence _compilePurpose = this.compilePurpose(z);
            _builder.append(_compilePurpose, "\t");
          }
        }
      }
    }
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    {
      EList<PrivateData> _privateData = p.getPrivateData();
      boolean _isEmpty_2 = _privateData.isEmpty();
      boolean _not_2 = (!_isEmpty_2);
      if (_not_2) {
        {
          EList<PrivateData> _privateData_1 = p.getPrivateData();
          for(final PrivateData y : _privateData_1) {
            CharSequence _compileDatum = this.compileDatum(y);
            _builder.append(_compileDatum, "\t");
          }
        }
      }
    }
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("D ALL-Information > ");
    {
      EList<PrivateData> _privateData_2 = p.getPrivateData();
      boolean _isEmpty_3 = _privateData_2.isEmpty();
      boolean _not_3 = (!_isEmpty_3);
      if (_not_3) {
        {
          EList<PrivateData> _privateData_3 = p.getPrivateData();
          boolean _hasElements = false;
          for(final PrivateData z_1 : _privateData_3) {
            if (!_hasElements) {
              _hasElements = true;
            } else {
              _builder.appendImmediate(",", "\t");
            }
            CharSequence _compileAll = this.compileAll(z_1);
            _builder.append(_compileAll, "\t");
          }
        }
      }
    }
    _builder.newLineIfNotEmpty();
    _builder.append("SPEC POLICY");
    _builder.newLine();
    _builder.append("\t");
    {
      EList<Collection> _collection = p.getCollection();
      boolean _isEmpty_4 = _collection.isEmpty();
      boolean _not_4 = (!_isEmpty_4);
      if (_not_4) {
        {
          EList<Collection> _collection_1 = p.getCollection();
          for(final Collection x_1 : _collection_1) {
            CharSequence _compileCollection = this.compileCollection(x_1);
            _builder.append(_compileCollection, "\t");
          }
        }
      }
    }
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    {
      EList<Disclosure> _disclosure = p.getDisclosure();
      boolean _isEmpty_5 = _disclosure.isEmpty();
      boolean _not_5 = (!_isEmpty_5);
      if (_not_5) {
        {
          EList<Disclosure> _disclosure_1 = p.getDisclosure();
          for(final Disclosure x_2 : _disclosure_1) {
            CharSequence _compileTransfer = this.compileTransfer(x_2);
            _builder.append(_compileTransfer, "\t");
          }
        }
      }
    }
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    {
      EList<Retention> _retention = p.getRetention();
      boolean _isEmpty_6 = _retention.isEmpty();
      boolean _not_6 = (!_isEmpty_6);
      if (_not_6) {
        {
          EList<Retention> _retention_1 = p.getRetention();
          for(final Retention x_3 : _retention_1) {
            CharSequence _compileRetention = this.compileRetention(x_3);
            _builder.append(_compileRetention, "\t");
          }
        }
      }
    }
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    {
      EList<Usage> _usage = p.getUsage();
      boolean _isEmpty_7 = _usage.isEmpty();
      boolean _not_7 = (!_isEmpty_7);
      if (_not_7) {
        {
          EList<Usage> _usage_1 = p.getUsage();
          for(final Usage x_4 : _usage_1) {
            CharSequence _compileUsage = this.compileUsage(x_4);
            _builder.append(_compileUsage, "\t");
          }
        }
      }
    }
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    {
      EList<Informative> _informative = p.getInformative();
      boolean _isEmpty_8 = _informative.isEmpty();
      boolean _not_8 = (!_isEmpty_8);
      if (_not_8) {
        {
          EList<Informative> _informative_1 = p.getInformative();
          for(final Informative x_5 : _informative_1) {
            CharSequence _compileInformative = this.compileInformative(x_5);
            _builder.append(_compileInformative, "\t");
          }
        }
      }
    }
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence compileAll(final PrivateData p) {
    StringConcatenation _builder = new StringConcatenation();
    String _description = p.getDescription();
    _builder.append(_description, "");
    return _builder;
  }
  
  public CharSequence compileActor(final Recipient r) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<RecipientPart> _recipientPart = r.getRecipientPart();
      boolean _isEmpty = _recipientPart.isEmpty();
      boolean _not = (!_isEmpty);
      if (_not) {
        _builder.append("A ");
        String _recipientName = r.getRecipientName();
        _builder.append(_recipientName, "");
        _builder.append(" > ");
        {
          EList<RecipientPart> _recipientPart_1 = r.getRecipientPart();
          boolean _hasElements = false;
          for(final RecipientPart part : _recipientPart_1) {
            if (!_hasElements) {
              _hasElements = true;
            } else {
              _builder.appendImmediate(",", "");
            }
            CharSequence _compileRecipientPart = this.compileRecipientPart(part);
            _builder.append(_compileRecipientPart, "");
          }
        }
      }
    }
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence compileRecipientPart(final RecipientPart p) {
    StringConcatenation _builder = new StringConcatenation();
    Recipient _recipientPart = p.getRecipientPart();
    String _recipientName = _recipientPart.getRecipientName();
    _builder.append(_recipientName, "");
    return _builder;
  }
  
  public CharSequence compilePurpose(final Service s) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<ServicePart> _servicePart = s.getServicePart();
      boolean _isEmpty = _servicePart.isEmpty();
      boolean _not = (!_isEmpty);
      if (_not) {
        _builder.append("P ");
        String _serviceName = s.getServiceName();
        _builder.append(_serviceName, "");
        _builder.append(" > ");
        {
          EList<ServicePart> _servicePart_1 = s.getServicePart();
          boolean _hasElements = false;
          for(final ServicePart pur : _servicePart_1) {
            if (!_hasElements) {
              _hasElements = true;
            } else {
              _builder.appendImmediate(",", "");
            }
            CharSequence _compilePurpose = this.compilePurpose(pur);
            _builder.append(_compilePurpose, "");
          }
        }
      }
    }
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence compilePurpose(final ServicePart p) {
    StringConcatenation _builder = new StringConcatenation();
    Service _servicePart = p.getServicePart();
    String _serviceName = _servicePart.getServiceName();
    _builder.append(_serviceName, "");
    return _builder;
  }
  
  public CharSequence compileDatum(final PrivateData pd) {
    StringConcatenation _builder = new StringConcatenation();
    {
      String _name = pd.getName();
      boolean _notEquals = (!Objects.equal(_name, null));
      if (_notEquals) {
        _builder.append("D ");
        String _description = pd.getDescription();
        _builder.append(_description, "");
        _builder.append(" > ");
        {
          EList<Attribute> _attribute = pd.getAttribute();
          boolean _hasElements = false;
          for(final Attribute pdat : _attribute) {
            if (!_hasElements) {
              _hasElements = true;
            } else {
              _builder.appendImmediate(",", "");
            }
            CharSequence _compileAttribute = this.compileAttribute(pdat);
            _builder.append(_compileAttribute, "");
          }
        }
      }
    }
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence compileAttribute(final Attribute a) {
    StringConcatenation _builder = new StringConcatenation();
    String _name = a.getName();
    _builder.append(_name, "");
    return _builder;
  }
  
  public CharSequence compileCollection(final Collection c) {
    StringConcatenation _builder = new StringConcatenation();
    {
      String _modality = c.getModality();
      boolean _equals = Objects.equal(_modality, "Permitted");
      if (_equals) {
        _builder.append("P ");
      } else {
        String _modality_1 = c.getModality();
        boolean _equals_1 = Objects.equal(_modality_1, "Obligation");
        if (_equals_1) {
          _builder.append("O ");
        } else {
          _builder.append("R ");
        }
      }
    }
    _builder.append("COLLECT ");
    {
      EList<RefPrivateData> _refPrivateData = c.getRefPrivateData();
      int _length = ((Object[])Conversions.unwrapArray(_refPrivateData, Object.class)).length;
      boolean _equals_2 = (_length == 8);
      if (_equals_2) {
        _builder.append("ALL-Information ");
      } else {
        {
          EList<RefPrivateData> _refPrivateData_1 = c.getRefPrivateData();
          boolean _hasElements = false;
          for(final RefPrivateData p : _refPrivateData_1) {
            if (!_hasElements) {
              _hasElements = true;
            } else {
              _builder.appendImmediate(",", "");
            }
            CharSequence _compile = this.compile(p);
            _builder.append(_compile, "");
            _builder.append(" ");
          }
        }
      }
    }
    {
      EList<RefService> _refService = c.getRefService();
      boolean _isEmpty = _refService.isEmpty();
      boolean _not = (!_isEmpty);
      if (_not) {
        _builder.append("FOR ");
        {
          EList<RefService> _refService_1 = c.getRefService();
          boolean _hasElements_1 = false;
          for(final RefService s : _refService_1) {
            if (!_hasElements_1) {
              _hasElements_1 = true;
            } else {
              _builder.appendImmediate(",", "");
            }
            CharSequence _compile_1 = this.compile(s);
            _builder.append(_compile_1, "");
          }
        }
      } else {
        _builder.append("FOR anything");
      }
    }
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence compileTransfer(final Disclosure d) {
    StringConcatenation _builder = new StringConcatenation();
    {
      String _modality = d.getModality();
      boolean _equals = Objects.equal(_modality, "Permitted");
      if (_equals) {
        _builder.append("P ");
      } else {
        String _modality_1 = d.getModality();
        boolean _equals_1 = Objects.equal(_modality_1, "Obligation");
        if (_equals_1) {
          _builder.append("O ");
        } else {
          _builder.append("R ");
        }
      }
    }
    _builder.append("TRANSFER ");
    {
      EList<RefPrivateData> _refPrivateData = d.getRefPrivateData();
      int _length = ((Object[])Conversions.unwrapArray(_refPrivateData, Object.class)).length;
      boolean _equals_2 = (_length == 8);
      if (_equals_2) {
        _builder.append("ALL-Information ");
      } else {
        {
          EList<RefPrivateData> _refPrivateData_1 = d.getRefPrivateData();
          boolean _hasElements = false;
          for(final RefPrivateData p : _refPrivateData_1) {
            if (!_hasElements) {
              _hasElements = true;
            } else {
              _builder.appendImmediate(",", "");
            }
            CharSequence _compile = this.compile(p);
            _builder.append(_compile, "");
            _builder.append(" ");
          }
        }
      }
    }
    {
      EList<RefRecipientSource> _refRecipientSource = d.getRefRecipientSource();
      boolean _isEmpty = _refRecipientSource.isEmpty();
      boolean _not = (!_isEmpty);
      if (_not) {
        _builder.append("FROM ");
        {
          EList<RefRecipientSource> _refRecipientSource_1 = d.getRefRecipientSource();
          boolean _hasElements_1 = false;
          for(final RefRecipientSource rs : _refRecipientSource_1) {
            if (!_hasElements_1) {
              _hasElements_1 = true;
            } else {
              _builder.appendImmediate(",", "");
            }
            CharSequence _compile_1 = this.compile(rs);
            _builder.append(_compile_1, "");
          }
        }
        _builder.append(" ");
      }
    }
    {
      EList<RefRecipientTarget> _refRecipientTarget = d.getRefRecipientTarget();
      boolean _isEmpty_1 = _refRecipientTarget.isEmpty();
      boolean _not_1 = (!_isEmpty_1);
      if (_not_1) {
        _builder.append("TO ");
        {
          EList<RefRecipientTarget> _refRecipientTarget_1 = d.getRefRecipientTarget();
          boolean _hasElements_2 = false;
          for(final RefRecipientTarget rt : _refRecipientTarget_1) {
            if (!_hasElements_2) {
              _hasElements_2 = true;
            } else {
              _builder.appendImmediate(",", "");
            }
            CharSequence _compile_2 = this.compile(rt);
            _builder.append(_compile_2, "");
          }
        }
        _builder.append(" ");
      }
    }
    {
      EList<RefService> _refService = d.getRefService();
      boolean _isEmpty_2 = _refService.isEmpty();
      boolean _not_2 = (!_isEmpty_2);
      if (_not_2) {
        _builder.append("FOR ");
        {
          EList<RefService> _refService_1 = d.getRefService();
          boolean _hasElements_3 = false;
          for(final RefService s : _refService_1) {
            if (!_hasElements_3) {
              _hasElements_3 = true;
            } else {
              _builder.appendImmediate(",", "");
            }
            CharSequence _compile_3 = this.compile(s);
            _builder.append(_compile_3, "");
          }
        }
      } else {
        _builder.append("FOR anything");
      }
    }
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence compileRetention(final Retention r) {
    StringConcatenation _builder = new StringConcatenation();
    {
      String _modality = r.getModality();
      boolean _equals = Objects.equal(_modality, "Permitted");
      if (_equals) {
        _builder.append("P ");
      } else {
        String _modality_1 = r.getModality();
        boolean _equals_1 = Objects.equal(_modality_1, "Obligation");
        if (_equals_1) {
          _builder.append("O ");
        } else {
          _builder.append("R ");
        }
      }
    }
    _builder.append("RETAIN ");
    {
      EList<RefPrivateData> _refPrivateData = r.getRefPrivateData();
      int _length = ((Object[])Conversions.unwrapArray(_refPrivateData, Object.class)).length;
      boolean _equals_2 = (_length == 8);
      if (_equals_2) {
        _builder.append("ALL-Information ");
      } else {
        {
          EList<RefPrivateData> _refPrivateData_1 = r.getRefPrivateData();
          boolean _hasElements = false;
          for(final RefPrivateData p : _refPrivateData_1) {
            if (!_hasElements) {
              _hasElements = true;
            } else {
              _builder.appendImmediate(",", "");
            }
            CharSequence _compile = this.compile(p);
            _builder.append(_compile, "");
            _builder.append(" ");
          }
        }
      }
    }
    {
      EList<RefService> _refService = r.getRefService();
      boolean _isEmpty = _refService.isEmpty();
      boolean _not = (!_isEmpty);
      if (_not) {
        _builder.append("FOR ");
        {
          EList<RefService> _refService_1 = r.getRefService();
          boolean _hasElements_1 = false;
          for(final RefService s : _refService_1) {
            if (!_hasElements_1) {
              _hasElements_1 = true;
            } else {
              _builder.appendImmediate(",", "");
            }
            CharSequence _compile_1 = this.compile(s);
            _builder.append(_compile_1, "");
          }
        }
      } else {
        _builder.append("FOR anything");
      }
    }
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence compileUsage(final Usage u) {
    StringConcatenation _builder = new StringConcatenation();
    {
      String _modality = u.getModality();
      boolean _equals = Objects.equal(_modality, "Permitted");
      if (_equals) {
        _builder.append("P ");
      } else {
        String _modality_1 = u.getModality();
        boolean _equals_1 = Objects.equal(_modality_1, "Obligation");
        if (_equals_1) {
          _builder.append("O ");
        } else {
          _builder.append("R ");
        }
      }
    }
    _builder.append("USE ");
    {
      EList<RefPrivateData> _refPrivateData = u.getRefPrivateData();
      int _length = ((Object[])Conversions.unwrapArray(_refPrivateData, Object.class)).length;
      boolean _equals_2 = (_length == 8);
      if (_equals_2) {
        _builder.append("ALL-Information ");
      } else {
        {
          EList<RefPrivateData> _refPrivateData_1 = u.getRefPrivateData();
          boolean _hasElements = false;
          for(final RefPrivateData p : _refPrivateData_1) {
            if (!_hasElements) {
              _hasElements = true;
            } else {
              _builder.appendImmediate(",", "");
            }
            CharSequence _compile = this.compile(p);
            _builder.append(_compile, "");
            _builder.append(" ");
          }
        }
      }
    }
    {
      EList<RefService> _refService = u.getRefService();
      boolean _isEmpty = _refService.isEmpty();
      boolean _not = (!_isEmpty);
      if (_not) {
        _builder.append("FOR ");
        {
          EList<RefService> _refService_1 = u.getRefService();
          boolean _hasElements_1 = false;
          for(final RefService s : _refService_1) {
            if (!_hasElements_1) {
              _hasElements_1 = true;
            } else {
              _builder.appendImmediate(",", "");
            }
            CharSequence _compile_1 = this.compile(s);
            _builder.append(_compile_1, "");
          }
        }
      } else {
        _builder.append("FOR anything");
      }
    }
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence compileInformative(final Informative i) {
    StringConcatenation _builder = new StringConcatenation();
    {
      String _modality = i.getModality();
      boolean _equals = Objects.equal(_modality, "Permitted");
      if (_equals) {
        _builder.append("P ");
      } else {
        String _modality_1 = i.getModality();
        boolean _equals_1 = Objects.equal(_modality_1, "Obligation");
        if (_equals_1) {
          _builder.append("O ");
        } else {
          _builder.append("R ");
        }
      }
    }
    _builder.append("INFORM ");
    {
      EList<RefPrivateData> _refPrivateData = i.getRefPrivateData();
      int _length = ((Object[])Conversions.unwrapArray(_refPrivateData, Object.class)).length;
      boolean _equals_2 = (_length == 8);
      if (_equals_2) {
        _builder.append("ALL-Information ");
        {
          EList<RefPrivateData> _refPrivateData_1 = i.getRefPrivateData();
          boolean _hasElements = false;
          for(final RefPrivateData p : _refPrivateData_1) {
            if (!_hasElements) {
              _hasElements = true;
            } else {
              _builder.appendImmediate(",", "");
            }
            CharSequence _compile = this.compile(p);
            _builder.append(_compile, "");
            _builder.append(" ");
          }
        }
      }
    }
    {
      EList<RefService> _refService = i.getRefService();
      boolean _isEmpty = _refService.isEmpty();
      boolean _not = (!_isEmpty);
      if (_not) {
        _builder.append("FOR ");
        {
          EList<RefService> _refService_1 = i.getRefService();
          boolean _hasElements_1 = false;
          for(final RefService s : _refService_1) {
            if (!_hasElements_1) {
              _hasElements_1 = true;
            } else {
              _builder.appendImmediate(",", "");
            }
            CharSequence _compile_1 = this.compile(s);
            _builder.append(_compile_1, "");
          }
        }
      } else {
        _builder.append("FOR anything");
      }
    }
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence compile(final RefRecipientSource rs) {
    StringConcatenation _builder = new StringConcatenation();
    Recipient _refRecipientSource = rs.getRefRecipientSource();
    String _recipientName = _refRecipientSource.getRecipientName();
    _builder.append(_recipientName, "");
    return _builder;
  }
  
  public CharSequence compile(final RefRecipientTarget rt) {
    StringConcatenation _builder = new StringConcatenation();
    Recipient _refRecipientTarget = rt.getRefRecipientTarget();
    String _recipientName = _refRecipientTarget.getRecipientName();
    _builder.append(_recipientName, "");
    return _builder;
  }
  
  public CharSequence compile(final RefPrivateData p) {
    StringConcatenation _builder = new StringConcatenation();
    PrivateData _refPrivateData = p.getRefPrivateData();
    String _description = _refPrivateData.getDescription();
    _builder.append(_description, "");
    return _builder;
  }
  
  public CharSequence compile(final RefService r) {
    StringConcatenation _builder = new StringConcatenation();
    Service _refService = r.getRefService();
    String _serviceName = _refService.getServiceName();
    _builder.append(_serviceName, "");
    return _builder;
  }
}
